#Build the Docker Image
#docker build -t maven-test-runner .
#Run the Docker Container
#docker run maven-test-runner

import yagmail

# Email configuration
sender = "sankarcherry1432@gmail.com"  # Replace with your Gmail address
password = "fjqv mgdn krab cnez"  # Replace with your Gmail password or app password
recipients = ["sankarcherry1432@gmail.com"]  # Add recipients

# Email content
subject = "Test Report - Build Results"
body = """
Hi Team,

The test run is complete. Please find the attached test report for your reference.

Regards,
Your Automation Bot
"""

# File to attach
attachment = "java_project/ExtentReports/Report.html"

# Send the email
try:
    yag = yagmail.SMTP(user=sender, password=password)
    yag.send(to=recipients, subject=subject, contents=body, attachments=attachment)
    print("Email sent successfully!")
except Exception as e:
    print(f"Error sending email: {e}")
