package com.example.student_survey.Controller;

import com.example.student_survey.model.Survey;
import com.example.student_survey.Service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @PostMapping("/post")
    public Survey createSurvey(@RequestBody Survey survey) {
        return surveyService.saveSurvey(survey);
    }

    @GetMapping
    public List<Survey> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/{id}")
    public Optional<Survey> getSurveyById(@PathVariable Long id) {
        return surveyService.getSurveyById(id);
    }

    @PutMapping("/{id}")
    public Survey updateSurvey(@PathVariable Long id, @RequestBody Survey survey) {
        return surveyService.updateSurvey(id, survey);
    }

    @DeleteMapping("/{id}")
    public void deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
    }
}
