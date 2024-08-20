<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body {
                font-family: Arial, sans-serif;
                color: #333;
                line-height: 1.6;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }
            .email-container {
                background-color: #fff;
                padding: 20px;
                margin: 30px auto;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                max-width: 600px;
                border: 1px solid #ddd;
            }
            p {
                margin: 0 0 15px;
            }
            ul {
                padding: 0;
                list-style-type: none;
            }
            ul li {
                margin: 5px 0;
            }
            ul li strong {
                color: #555;
            }
            .footer {
                margin-top: 20px;
                font-size: 14px;
                color: #777;
                text-align: center;
            }
            .greeting {
                font-size: 18px;
                font-weight: bold;
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <div class="email-container">
            <p class="greeting">Bonjour ${clientName!"Cher client"},</p>

            <p>Nous sommes heureux de vous accueillir parmi nos utilisateurs. Voici vos informations de connexion pour accéder à notre plateforme :</p>

            <ul>
                <li><strong>Nom d'utilisateur :</strong> ${username!"Nom d'utilisateur non spécifié"}</li>
                <li><strong>Mot de passe :</strong> ${tempPassword!"Mot de passe temporaire non spécifié"}</li>
            </ul>

            <p>Pour des raisons de sécurité, nous vous recommandons de changer votre mot de passe lors de votre première connexion.</p>

            <p>De plus, l'administration vous contactera prochainement pour valider toutes vos informations et vous fournir l'accès au profil administrateur.</p>

            <p>Si vous rencontrez des problèmes pour vous connecter ou si vous avez des questions, n'hésitez pas à nous contacter.</p>

            <p>Cordialement,</p>
            <p><strong>OrderWise</strong></p>

            <div class="footer">
                <p>© 2024 OrderWise. Tous droits réservés.</p>
            </div>
        </div>
    </body>
</html>
