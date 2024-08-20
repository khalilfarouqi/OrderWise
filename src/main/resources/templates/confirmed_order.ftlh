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

            <p>Nous sommes ravis de vous informer que votre commande a été confirmée avec succès. Voici les détails de votre commande :</p>

            <ul>
                <li><strong>Numéro de commande :</strong> ${orderNumber!"Numéro de commande non spécifié"}</li>
                <li><strong>Date de commande :</strong> ${orderDate!"Date non spécifiée"}</li>
                <li><strong>Montant total :</strong> ${orderTotal!"Montant non spécifié"}</li>
            </ul>

            <p>Vous recevrez une notification dès que votre commande sera expédiée.</p>

            <p>Si vous avez des questions ou des préoccupations concernant votre commande, n'hésitez pas à nous contacter.</p>

            <p>Cordialement,</p>
            <p><strong>OrderWise</strong></p>

            <div class="footer">
                <p>© 2024 OrderWise. Tous droits réservés.</p>
            </div>
        </div>
    </body>
</html>
