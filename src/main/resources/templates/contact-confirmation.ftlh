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
            h1 {
                color: #333;
                font-size: 24px;
                margin-bottom: 20px;
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
        </style>
    </head>
    <body>
        <div class="email-container">
            <p>Cher/Chère ${clientFullName},</p>

            <p>Nous avons bien reçu votre message et nous vous remercions de nous avoir contactés. Notre équipe de support est en train de traiter votre demande et vous répondra dans les plus brefs délais.</p>

            <p>Voici un récapitulatif de votre message :</p>
            <ul>
                <li><strong>Votre nom :</strong> ${clientFullName}</li>
                <li><strong>Adresse e-mail :</strong> ${clientEmail}</li>
                <li><strong>Message :</strong> ${clientMessage}</li>
            </ul>

            <p>Si vous avez des questions ou besoin d'aide supplémentaire, n'hésitez pas à nous contacter à nouveau.</p>

            <p>Merci d'avoir choisi OrderWise. Nous nous réjouissons de vous servir et de vous garantir une excellente expérience avec nous.</p>

            <p>Cordialement,</p>
            <p><strong>Khalil Farouqi</strong></p>
            <p>CEO</p>
            <p>OrderWise</p>

            <div class="footer">
                <p>© 2024 OrderWise. Tous droits réservés.</p>
            </div>
        </div>
    </body>
</html>
