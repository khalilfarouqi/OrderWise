<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
    </head>
    <body>
        <p>Bonjour ${clientName!"Cher client"},</p><br>
        <p>Nous sommes ravis de vous informer que votre commande a été confirmée avec succès. Voici les détails de votre commande :</p>

        <ul>
            <li><strong>Numéro de commande :</strong> ${orderNumber!"Numéro de commande non spécifié"}</li>
            <li><strong>Date de commande :</strong> ${orderDate!"Date non spécifiée"}</li>
            <li><strong>Montant total :</strong> ${orderTotal!"Montant non spécifié"}</li>
        </ul>

        <p>Vous recevrez une notification dès que votre commande sera expédiée.</p><br>
        <p>Si vous avez des questions ou des préoccupations concernant votre commande, n'hésitez pas à nous contacter.</p><br>
        <p>Cordialement,</p><br>
        <p>OrderWise</p>
    </body>
</html>
