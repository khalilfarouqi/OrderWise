<#-- Email template to be sent after validating a user's profile -->

<#-- Setting the variables for the email -->
<#assign applicationName = "OrderWise">
<#assign supportEmail = "bio.khalil.f@gmail.com">
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Profile Validation Successful</title>
    </head>
    <body style="font-family: Arial, sans-serif; line-height: 1.6;">
        <table width="100%" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td align="center" bgcolor="#f7f7f7" style="padding: 20px 0;">
                    <table width="600" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td align="center" bgcolor="#ffffff" style="padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);">
                                <h1 style="color: #333;">Profile Validation Successful</h1>
                                <p style="color: #555;">Dear <strong>${userName}</strong>,</p>
                                <p style="color: #555;">
                                    We are pleased to inform you that your profile has been successfully validated in <strong>${applicationName}</strong>.
                                </p>
                                <p style="color: #555;">
                                    You can now access all the features and services available to our validated users. If you have any questions or need further assistance, please do not hesitate to contact our support team.
                                </p>
                                <p style="color: #555;">
                                    Thank you for choosing <strong>${applicationName}</strong>.
                                </p>
                                <p style="color: #555;">Best regards,</p>
                                <p style="color: #555;"><strong>The ${applicationName} Team</strong></p>
                                <p style="color: #aaa; font-size: 12px;">
                                    If you have any questions, feel free to contact us at <a href="mailto:${supportEmail}" style="color: #555;">${supportEmail}</a>.
                                </p>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </body>
</html>
