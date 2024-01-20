let url = window.location.href
let parameterString = url.substring(url.lastIndexOf("?") + 1)
let parameterStringList = parameterString.split("&")
let parameters = retrieveParameters()

function retrieveParameters() {
    let paramMap = new Map()

    for (var i = 0; i < parameterStringList.length; i++) {
        let param = parameterStringList[i]
        console.info(param)
        let splitParam = param.split("=")
        let paramKey = splitParam[0]
        let paramValue = splitParam[1]

        paramMap.set(paramKey, paramValue)
    }

    return paramMap
}

function evaluateCallback() {
    if (parameters.has("error")) {
        callbackFail()
    }

    if (parameters.has("code")) {
        callbackOk()
    }
}

let callbackResult = document.getElementById("callback-result")

function callbackOk() {
    callbackResult.innerText = "The Authorization was successful, you can now close this window."
}

function callbackFail() {
    callbackResult.innerText = 
    `The Authorization was unsuccessful, please try again.
     If it still doesn't work, please open an issue on the GitHub.`
}

