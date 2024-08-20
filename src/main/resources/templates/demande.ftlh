<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Demande de Retrait</title>
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
                text-align: center;
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
        </style>
    </head>
    <body>
        <div class="email-container">
            <h1>Demande de Retrait</h1>
            <p>Bonjour ${fullName},</p>
            <p>Votre demande de retrait de ${montant} DH a été enregistrée avec succès.</p>
            <p>Nous allons prendre en considération votre demande dans les plus brefs délais et nous vous enverrons une réponse.</p>
            <p>Merci de votre confiance.</p>
            <p>Cordialement,</p>
            <p><strong>L'équipe de Support</strong></p>
            <div class="footer">
                <p>© 2024 OrderWise. Tous droits réservés.</p>
            </div>
        </div>
    </body>
</html>
