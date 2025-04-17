package com.example.student_survey.Service;

import com.example.student_survey.model.Survey;
import com.example.student_survey.Repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public Survey saveSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Optional<Survey> getSurveyById(Long id) {
        return surveyRepository.findById(id);
    }

    @Override
    public Survey updateSurvey(Long id, Survey updatedSurvey) {
        Survey existing = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found with id: " + id));
        existing.setFirstName(updatedSurvey.getFirstName());
        existing.setLastName(updatedSurvey.getLastName());
        existing.setStreetAddress(updatedSurvey.getStreetAddress());
        existing.setCity(updatedSurvey.getCity());
        existing.setState(updatedSurvey.getState());
        existing.setZip(updatedSurvey.getZip());
        existing.setPhone(updatedSurvey.getPhone());
        existing.setEmail(updatedSurvey.getEmail());
        existing.setSurveyDate(updatedSurvey.getSurveyDate());
        existing.setLikedMost(updatedSurvey.getLikedMost());
        existing.setInterestSource(updatedSurvey.getInterestSource());
        existing.setRecommendationLikelihood(updatedSurvey.getRecommendationLikelihood());
        return surveyRepository.save(existing);
    }

    @Override
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }
}
