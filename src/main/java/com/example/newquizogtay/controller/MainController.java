package com.example.newquizogtay.controller;

import java.io.IOException;
import java.util.List;

import com.example.newquizogtay.model.QuestionForm;
import com.example.newquizogtay.model.Result;
import com.example.newquizogtay.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

	// Injection des dépendances
	@Autowired
	Result result; // Instance de la classe Result
	@Autowired
	QuizService quizService; // Instance de la classe QuizService

	// Variable pour suivre si le quiz a été soumis ou non
	Boolean submitted = false;

	// Fournir l'objet Result aux vues comme un attribut avec le nom "result"
	@ModelAttribute("result")
	public Result getResult() {
		return result;
	}

	// Gérer les requêtes GET à la racine de l'application
	@GetMapping("/")
	public String home() {
		return "index.html"; // Retourne le nom de la vue à afficher
	}

	// Gérer les requêtes POST pour commencer le quiz
	@PostMapping("/quiz")
	public String quiz(@RequestParam String username, Model m, RedirectAttributes ra) throws IOException {
		if(username.equals("")) { // Vérifier si le nom d'utilisateur est vide
			ra.addFlashAttribute("warning", "You must enter your name"); // Ajouter un message flash
			return "redirect:/"; // Rediriger vers la page d'accueil
		}

		submitted = false; // Réinitialiser le statut de soumission
		result.setUsername(username); // Définir le nom d'utilisateur dans l'objet Result

		// Obtenir les questions du quiz
		QuestionForm qForm = quizService.getQuestions();
		m.addAttribute("qForm", qForm); // Ajouter les questions au modèle

		return "quiz.html"; // Retourner le nom de la vue à afficher
	}

	// Gérer les requêtes POST pour soumettre les réponses du quiz
	@PostMapping("/submit")
	public String submit(@ModelAttribute QuestionForm qForm, Model m) {
		if(!submitted) { // Vérifier si le quiz n'a pas déjà été soumis
			result.setTotalCorrect(quizService.getResult(qForm)); // Calculer le nombre de réponses correctes
			quizService.saveScore(result); // Enregistrer le score
			submitted = true; // Mettre à jour le statut de soumission
		}

		return "result.html"; // Retourner le nom de la vue à afficher
	}

	// Gérer les requêtes GET pour afficher le tableau des scores
	@GetMapping("/score")
	public String score(Model model) {
		List<Result> scoreList = quizService.getTopScore(); // Obtenir les meilleurs scores
		model.addAttribute("scoreList", scoreList); // Ajouter la liste des scores au modèle

		return "scoreboard.html"; // Retourner le nom de la vue à afficher
	}

}
