<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <p>Bonjour ${clientName!"Cher client"},</p><br>

    <p>Nous sommes heureux de vous accueillir parmi nos utilisateurs. Voici vos informations de connexion pour accéder à notre plateforme :</p>

    <ul>
        <li><strong>Nom d'utilisateur :</strong> ${username!"Nom d'utilisateur non spécifié"}</li>
        <li><strong>Mot de passe :</strong> ${tempPassword!"Mot de passe temporaire non spécifié"}</li>
    </ul>

    <p>Pour des raisons de sécurité, nous vous recommandons de changer votre mot de passe lors de votre première connexion.</p><br>

    <p>De plus, l'administration vous contactera prochainement pour valider toutes vos informations et vous fournir l'accès au profil administrateur.</p><br>

    <p>Si vous rencontrez des problèmes pour vous connecter ou si vous avez des questions, n'hésitez pas à nous contacter.</p><br>

    <p>Cordialement,</p><br>
    <p>OrderWise</p>
</body>
</html>
