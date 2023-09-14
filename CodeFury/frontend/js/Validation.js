// Import validations
function ImportVal(){
    let imf = document.forms["importForm"]["fname"].value;
    let ime = document.forms["importForm"]["email"].value;
    if(imf==""){
        alert("Name cannot be empty");
        return false;
    }
    else if(ime==""){
        alert("Email cannot be empty");
        return false;
    }
    return true;
}
function Iconf(){
    let imv = ImportVal();
    if(imv==true){
        alert("User successfully imported");
        let imtags = document.getElementById("importButton");
        imtags.innerHTML = "Import";
        imtags.href = "../html/registerUser.html";
    }
}
//register validations
function RegisterVal(){
    let ree = document.forms["registerForm"]["email"].value;
    let rep = document.forms["registerForm"]["password"].value;
    let recp = document.forms["registerForm"]["confirmPassword"].value;
    if(ree==""){
        alert("Email cannot be empty");
        return false;
    }
    else if(rep==""){
        alert("Password cannot be empty");
        return false;
    }
    else if(recp=="" || recp != rep){
        alert("Password value not matching");
        return false;
    }
    return true;
}
function Rconf(){
    let rev = RegisterVal();
    if(rev==true){
        alert("User successfully Registered!");
        let retags = document.getElementById("registerButton");
        retags.innerHTML = "Register";
        retags.href = "../html/loginUser.html";
    }
}
// login validation
function LoginVal(){
    let loe = document.forms["loginForm"]["email"].value;
    let lop = document.forms["loginForm"]["password"].value;
    let lot = document.forms["loginForm"]["type"].value;
    if(loe==""){
        alert("Please enter email");
        return 0;
    }
    else if(lot==""){
        alert("Please select type");
        return 0;
    }
    else if(lop==""){
        alert("Please enter password");
        return 0;
    }
    else if(lot=="projectManager"){
        return 1;
    }
    else if(lot=="developer"){
        return 2;
    }
    else if(lot=="tester"){
        return 3;
    }
    return 1;
}
function Lconf(){
    let loval = LoginVal();
    if(loval==1){
        alert("Project Manager successfully logged in");
        let imtags = document.getElementById("loginButton");
        imtags.innerHTML = "Login";
        imtags.href = "../html/projectHome.html";
    }
    if(loval==2){
        alert("Developer successfully logged in");
        let imtags = document.getElementById("loginButton");
        imtags.innerHTML = "Login";
        imtags.href = "../html/developerHome.html";
    }
    if(loval==3){
        alert("Tester successfully logged in");
        let imtags = document.getElementById("loginButton");
        imtags.innerHTML = "Login";
        imtags.href = "../html/testerHome.html";
    }
}