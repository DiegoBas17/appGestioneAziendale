package com.example.appGestioneAziendale.services;

import com.example.appGestioneAziendale.controllers.ComunicazioneScheduledController;
import com.example.appGestioneAziendale.domain.dto.requests.ComunicazioneAziendaleRequest;
import com.example.appGestioneAziendale.domain.dto.requests.ComunicazioneScheduledRequest;
import com.example.appGestioneAziendale.domain.dto.requests.ComunicazioneScheduledUpdateRequest;
import com.example.appGestioneAziendale.domain.dto.response.EntityIdResponse;
import com.example.appGestioneAziendale.domain.entities.ComunicazioneScheduled;
import com.example.appGestioneAziendale.domain.entities.Dipendente;
import com.example.appGestioneAziendale.domain.exceptions.IllegalTransactionException;
import com.example.appGestioneAziendale.domain.exceptions.MyEntityNotFoundException;
import com.example.appGestioneAziendale.repository.ComunicazioneScheduledRepository;
import com.github.javafaker.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.Trigger;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;

@Service
public class ComunicazioneScheduledService implements Job {

    @Autowired
    private ComunicazioneScheduledRepository comunicazioneScheduledRepository;

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private ComunicazioneAziendaleService comunicazioneService;

    @Autowired
    private Scheduler scheduler;

    public EntityIdResponse createComunicazioneScheduled(ComunicazioneScheduledRequest request)
            throws MyEntityNotFoundException, IllegalTransactionException, SchedulingException {
        // Verifico che il dipendente esista e lo prendo
        Dipendente dipendente = dipendenteService.getById(request.idDipendente().getId());

        ComunicazioneScheduled comunicazioneScheduled = ComunicazioneScheduled.builder()
                .testo(request.testo())
                .allegato_url(request.allegato_url())
                .publishTime(request.publishTime())
                .idDipendente(dipendente)
                .build();

        comunicazioneScheduledRepository.save(comunicazioneScheduled);

        // Creazione job e trigger
        JobDetail jobDetails = buildJobDetail(comunicazioneScheduled);
        Trigger trigger = buildJobTrigger(jobDetails, Date.from(
                comunicazioneScheduled.getPublishTime().atZone(ZoneId.systemDefault()).toInstant()));

        scheduler.scheduleJob(jobDetails, trigger);

        return EntityIdResponse.builder().id(comunicazioneScheduled.getId()).build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, Date publishTime) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .startAt(publishTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .build();
    }

    private JobDetail buildJobDetail(ComunicazioneScheduled comunicazioneScheduled) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("id", comunicazioneScheduled.getId());
        jobDataMap.put("entityData", comunicazioneScheduled);

        return JobBuilder.newJob(ComunicazioneScheduledService.class)
                .withIdentity(String.valueOf(comunicazioneScheduled.getId()), "comunicazioni")
                .storeDurably()
                .setJobData(jobDataMap)
                .build();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        Long scheduledId = jobDataMap.getLong("id");

        try {
            // Callback metodo generazione comunicazione aziendale (ipotetico)
            Dipendente dipendente = comunicazioneScheduledRepository.findById(scheduledId)
                    .orElseThrow(() -> new JobExecutionException("Comunicazione non trovata")).getIdDipendente();

            ComunicazioneAziendaleRequest request = ComunicazioneAziendaleRequest.builder()
                    .idDipendente(dipendente.getId())
                    .tipoOperazione("comunicazione")
                    .build();
            comunicazioneService.createComunicazioneAziendale(request);
            comunicazioneScheduledRepository.deleteById(scheduledId); // Elimino
        } catch (Exception e) {
            throw new JobExecutionException("Errore durante l'esecuzione della comunicazione", e);
        }
    }

    public EntityIdResponse updateComunicazioneScheduled(Long id, ComunicazioneScheduledUpdateRequest request)
            throws SchedulerException, IllegalTransactionException {
        ComunicazioneScheduled comunicazioneScheduled = comunicazioneScheduledRepository.findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("Comunicazione non trovata"));

        // Rimuovo job precedente
        JobKey jobKey = new JobKey(String.valueOf(comunicazioneScheduled.getId()), "comunicazioni");
        scheduler.deleteJob(jobKey);

        // Aggiornamento entità e ricreazione Job
        comunicazioneScheduled.setPublishTime(request.publishTime());
        comunicazioneScheduledRepository.save(comunicazioneScheduled);

        return createComunicazioneScheduled(ComunicazioneScheduledRequest.builder()
                .id(comunicazioneScheduled.getId())
                .testo(comunicazioneScheduled.getTesto())
                .publishTime(comunicazioneScheduled.getPublishTime())
                .idDipendente(comunicazioneScheduled.getIdDipendente())
                .build());
    }

    public void deleteComunicazioneScheduledById(Long id) throws SchedulingException {
        ComunicazioneScheduledController comunicazioneScheduled = comunicazioneScheduledRepository
                .findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("Comunicazione schedulata con" + id + " non trovata"));

        JobKey jobKey = new JobKey(String.valueOf(comunicazioneScheduled.getId()), "comunicazioni");
        scheduler.deleteJob(jobKey);

        comunicazioneScheduledRepository.deleteById(id);
    }
}