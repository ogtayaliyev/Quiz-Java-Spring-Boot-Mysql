 # Application Quiz utilisant Java Spring Boot MVC et MySQL by Ogtay

Cette application Quiz est développée en utilisant Java Spring Boot avec le modèle MVC et une base de données MySQL. L'application lit les questions à partir d'un fichier JSON nommé questions.json.

**_Installation_**
Assurez-vous d'avoir Java JDK installé sur votre système. Vous pouvez le télécharger depuis le site officiel d'Oracle.
Installez Apache Maven si ce n'est pas déjà fait. Vous pouvez le télécharger depuis le site officiel de Maven.
Assurez-vous que MySQL est installé sur votre système. Vous pouvez le télécharger depuis le site officiel de MySQL.
Clonez ce dépôt GitHub sur votre machine locale :
bash

**git clone https://github.com/votre-utilisateur/quiz-app.git**

Importez le projet dans votre IDE préféré (par exemple, IntelliJ IDEA ou Eclipse).
Configurez votre base de données MySQL en créant une base de données nommée quizdb. Vous pouvez également modifier les paramètres de connexion dans le fichier application.properties.
Exécutez le script SQL fourni dans le dossier sql pour créer la table questions et insérer des données de test.
Compilez et exécutez l'application en utilisant Maven :
arduino

**mvn spring-boot:run**

Utilisation
Une fois l'application en cours d'exécution, accédez à l'URL suivante dans votre navigateur :

**http://localhost:8080**

Vous serez dirigé vers la page d'accueil de l'application où vous pourrez commencer à répondre aux questions du quiz.

Structure du projet
src/main/java/com/example/quizapp: Contient les fichiers Java source de l'application.
src/main/resources: Contient les ressources de l'application, y compris le fichier application.properties pour la configuration.
questions.json: Contient les questions du quiz au format JSON.
sql: Contient le script SQL pour initialiser la base de données.

## Contribution
Les contributions sont les bienvenues ! Si vous souhaitez contribuer à ce projet, n'hésitez pas à ouvrir une issue ou à soumettre une pull request.
