#!/bin/sh

APP_DIR="@app/application-d628b4c4-0e7f-4a07-bcee-b03af811d541.ipa"
USER_NAME="appiumtester_yHvQVI"
ACCESS_KEY="ovWxpHg7gaUStb4Z73Lb"
BS_URL="https://api-cloud.browserstack.com/app-automate/upload"

function loadApp() {
    curl -u "${USER_NAME}:${ACCESS_KEY}" \
      -X POST "${BS_URL}" \
      -F "file=${APP_DIR}"
}

function runAppTest() {
    mvn clean test -Dcucumber.filter.tags="@appiumTest"
}

echo "Started Load App"
loadApp
echo "Started App Test"
runAppTest


