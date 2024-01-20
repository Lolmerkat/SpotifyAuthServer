let themeToggle = document.getElementById("theme-toggle")
let body = document.getElementsByName("body").item(0)
let icons = document.getElementsByTagName("fa-icon")

var currentTheme = body.getAttribute("theme")


function setTheme(theme) {
    body.setAttribute("theme", theme)
    var expires = new Date(Date.now() + 86400 * 31000).toUTCString()
    document.cookie = "theme=" + encodeURIComponent(currentTheme) + "; expires=" + expires
}

function getTheme() {
    return body.getAttribute("theme")
}

function toggleTheme() {
    currentTheme = (currentTheme == "dark") ? "light" : "dark"
    setTheme(currentTheme)
}

function getCookie(cookieName) {
    let cookies = document.cookie.split(";")

    for (var i = 0; i < cookies.length; i++) {
        var currentCookie = cookies[i].trim()

        if (currentCookie.toString().indexOf(cookieName + "=") === 0) {
            return decodeURIComponent(currentCookie.substring(cookieName.length + 1))
        }
    }
    return null
}

currentTheme = (getCookie("theme") != null) ? getCookie("theme") : "dark"
setTheme(currentTheme)