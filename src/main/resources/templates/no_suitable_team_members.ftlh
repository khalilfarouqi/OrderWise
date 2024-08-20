<#-- Freemarker Template: No Suitable Team Members for Delivery -->

<#macro emailTemplate subject>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${subject}</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                color: #333;
                line-height: 1.6;
                padding: 20px;
            }
            .email-container {
                background-color: #ffffff;
                border: 1px solid #dddddd;
                padding: 20px;
                margin: auto;
                max-width: 600px;
                box-shadow: 0 2px 3px rgba(0,0,0,0.1);
            }
            .email-header {
                font-size: 24px;
                color: #0066cc;
                border-bottom: 2px solid #eeeeee;
                padding-bottom: 10px;
                margin-bottom: 20px;
            }
            .email-body {
                font-size: 16px;
            }
            .email-footer {
                margin-top: 20px;
                font-size: 14px;
                color: #777777;
            }
        </style>
    </head>
    <body>
    <div class="email-container">
        <div class="email-header">
            ${subject}
        </div>
        <div class="email-body">
            Dear ${recipientName},

            <p>I wanted to inform you that, unfortunately, no suitable team members are currently available for the upcoming delivery.</p>

            <p>Please let me know how you would like to proceed.</p>
        </div>
        <div class="email-footer">
            Best regards,<br>
            Khalil Farouqi
        </div>
    </div>
    </body>
    </html>
</#macro>

<#-- Usage Example -->
<@emailTemplate subject="No Suitable Team Members Available for Delivery">
    <#assign recipientName="Recipient's Name"/>
</@emailTemplate>
