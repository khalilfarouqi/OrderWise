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

            <p>Nous tenons à vous informer que votre compte avec OrderWise a été supprimé avec succès.</p>

            <p>Nous sommes désolés de vous voir partir et nous apprécions le temps que vous avez passé avec nous. Si vous avez des questions, des préoccupations, ou si vous avez besoin d'une assistance supplémentaire, n'hésitez pas à contacter notre équipe de support à ${supportEmailAddress}.</p>

            <p>Merci d'avoir fait partie de OrderWise. Nous vous souhaitons tout le meilleur.</p>

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
