package com.example.newquizogtay.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.newquizogtay.model.Question;
import com.example.newquizogtay.model.QuestionForm;
import com.example.newquizogtay.model.Result;
import com.example.newquizogtay.repository.QuestionRepo;
import com.example.newquizogtay.repository.ResultRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
	private final ObjectMapper objectMapper;

	// Injection des dépendances
	@Autowired
	QuestionForm questionFrom;
	@Autowired
	ResultRepo resultRepository;

	// Constructeur prenant en charge l'injection de dépendances
	@Autowired
	public QuizService(ObjectMapper objectMapper, ResultRepo resultRepository) {
		this.objectMapper = objectMapper;
		this.resultRepository = resultRepository;
	}

	// Méthode pour obtenir un ensemble de questions à partir d'un fichier JSON
	public QuestionForm getQuestions() throws IOException {
		List<Question> questionList = new ArrayList<>();

		// Lecture des questions à partir du fichier JSON
		InputStream inputStream = getClass().getResourceAsStream("/question.json");
		List<Question> allQues = objectMapper.readValue(inputStream, new TypeReference<>() {
        });

		// Sélection aléatoire de 5 questions parmi toutes les questions disponibles
		Random random = new Random();
		for(int i=0; i<5; i++) {
			int rand = random.nextInt(allQues.size());
			questionList.add(allQues.get(rand));
			allQues.remove(rand);
		}

		// Création du formulaire de questions à partir des questions sélectionnées
		questionFrom.setQuestions(questionList);

		return questionFrom;
	}

	// Méthode pour calculer le nombre de réponses correctes
	public int getResult(QuestionForm questionFrom) {
		int correct = 0;

		// Vérification des réponses données par l'utilisateur
		for(Question question: questionFrom.getQuestions())
			if(question.getReponse() == question.getChose())
				correct++;

		return correct;
	}

	// Méthode pour enregistrer le score dans la base de données
	public void saveScore(Result result) {
		Result saveResult = new Result();
		saveResult.setUsername(result.getUsername());
		saveResult.setTotalCorrect(result.getTotalCorrect());
		resultRepository.save(saveResult);
	}

	// Méthode pour obtenir les meilleurs scores depuis la base de données
	public List<Result> getTopScore() {
		// Récupération des résultats triés par ordre décroissant du nombre de réponses correctes
		List<Result> scoreList = resultRepository.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));

		return scoreList;
	}
}
