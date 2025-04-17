package com.example.student_survey.Service;

import com.example.student_survey.model.Survey;
import java.util.List;
import java.util.Optional;

public interface SurveyService {
    Survey saveSurvey(Survey survey);

    List<Survey> getAllSurveys();

    Optional<Survey> getSurveyById(Long id);

    Survey updateSurvey(Long id, Survey updatedSurvey);

    void deleteSurvey(Long id);
}
