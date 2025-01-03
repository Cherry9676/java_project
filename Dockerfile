# Use an official Maven image as the base
FROM maven:3.8.6-openjdk-17

# Install Chrome and dependencies
RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    unzip && \
    wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && apt-get install -y google-chrome-stable

# Install ChromeDriver
RUN CHROME_DRIVER_VERSION=$(curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE) && \
    wget -N https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip && \
    mv chromedriver /usr/local/bin/ && \
    chmod +x /usr/local/bin/chromedriver && \
    rm chromedriver_linux64.zip

# Install Python and pip
RUN apt-get install -y python3 python3-pip

# Install Python email-sending library
RUN pip3 install yagmail

# Set display port for Chrome in headless mode
ENV DISPLAY=:99

# Set working directory inside the container
WORKDIR /app

# Copy project files into the container
COPY . .

# Pre-build the Maven project
RUN mvn clean install

# Copy Python email script
COPY send_email.py .

# Run Maven tests and send the report via email
CMD mvn clean test -f java_project/pom.xml && python3 send_email.py
