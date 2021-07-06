import axios from "axios";

export function getFromWS(success, failure, resource_url, config) {
    fetch(`${process.env.REACT_APP_URL_API}/${resource_url}`, { headers: config})
        .then((res) => res.json())
        .then((res) => {success(res)})
        .catch((err) => failure(err.message));
}

export function getFromWS2(success, failure, resource_url, config) {
    fetch(`${resource_url}`, { headers: config})
        .then((res) => res.json())
        .then((res) => {success(res); console.log(res)})
        .catch((err) => failure(err.message));
}

export function postToWS(success, failure, form, dispatch, resource_url, config) {
    console.log(config)
    console.log(resource_url)
    axios.post(`${process.env.REACT_APP_URL_API}/${resource_url}`, form, { headers: config})
        .then((res) => {
            dispatch(success(res)) ;
        })
        .catch(error => {
            dispatch(failure(error.response.data));
        })
}

export function postToWS2(success, failure, form, dispatch, resource_url, config) {
    console.log(config)
    axios.post(`${resource_url}`, form, { headers: config})
        .then((res) => {
            dispatch(success(res));
        })
        .catch(error => {
            dispatch(failure(error.response.data));
        })
}

export function deleteToWS(success, failure, form, dispatch, resource_url, config) {
    console.log(config)
    console.log(resource_url)
    axios.delete(`${process.env.REACT_APP_URL_API}/${resource_url}`,{
        headers: config,
        data: form
    })
    .then((res) => {
            dispatch(success(res));
        })
        .catch(error => {
            dispatch(failure(error.message.data));
        })
}

export function optionsFromWS(success, failure, dispatch, resource_url, config) {
    console.log(resource_url)
    console.log(config)
    fetch(`${process.env.REACT_APP_URL_API}${resource_url}`, { method: `options`, headers: config})
        .then((res) => res.json())
        .then((res) => {
            dispatch(success(res))
            console.log(res)
        })
        .catch((err) => failure(err.message));
    console.log("Passo 2")
}

export function signInToWS(success, failure, credentials, dispatch) {
    axios.post(`${process.env.REACT_APP_URL_API}/auth/login`, credentials)
        .then((res) => {
            dispatch(success(res));
        })
        .catch(error => {
            let errorMessage;
            if (error.message.includes("401")) {
                errorMessage = "Invalid username/password!";
            } else {
                errorMessage = "Unable to connect! Please try again or later.";
            }
            dispatch(failure(errorMessage));
        })
}

export function signUpToWS(success, failure, info, dispatch) {
    axios.post(`${process.env.REACT_APP_URL_API}/auth/signup`, info)
        .then(() => {
            dispatch(success("Welcome! Registration completed successfully."));
        })
        .catch(error => {
            let errorMessage;
            if (error.message.includes("400")) {
                errorMessage = error.response.data.errorMessage;
            } else if (error.message.includes("500")) {
                errorMessage = "Failure to register user, please check submitted data!";
            } else {
                errorMessage = "Unable to connect! Please try again or later.";
            }
            dispatch(failure(errorMessage));
        })
}
