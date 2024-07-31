<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
    </head>
    <body>
        <p>Bonjour ${clientName!"Cher client"},</p><br>
        <p>Nous regrettons de vous informer que votre commande a été annulée. Voici les détails de votre commande annulée :</p>

        <ul>
            <li><strong>Numéro de commande :</strong> ${orderNumber!"Numéro de commande non spécifié"}</li>
            <li><strong>Date de commande :</strong> ${orderDate!"Date non spécifiée"}</li>
        </ul>
        <p>Si vous avez des questions ou des préoccupations concernant cette annulation, veuillez nous contacter pour plus d'informations.</p><br>
        <p>Nous nous excusons pour tout inconvénient causé et espérons vous servir à nouveau à l'avenir.</p><br>
        <p>Cordialement,</p><br>
        <p>OrderWise</p>
    </body>
</html>
