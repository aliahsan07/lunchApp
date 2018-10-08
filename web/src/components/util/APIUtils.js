import { API_BASE_URL, ACCESS_TOKEN } from '../constants/constants';

const request = (options) => {
    const headers = new Headers({
        'Content-Type': 'application/json',
    })
    
    if(localStorage.getItem(ACCESS_TOKEN)) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
    }

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    return fetch(options.url, options)
    .then(response => 
        response.json().then(json => {
            if(!response.ok) {
                return Promise.reject(json);
            }
            return json;
        })
    );
};


export function getUserProfile(username) {
    return request({
        url: API_BASE_URL + "/users/" + username,
        method: 'GET'
    });
}


export function submitOrder(order) {

    console.log(JSON.stringify(order));

    return request({
        url: API_BASE_URL + '/customers/orders',
        method: 'POST',
        body: JSON.stringify(order) 
    })
}


export function PostData(type, userData){

    let url = API_BASE_URL;

    return new Promise((resolve, reject) => {

        fetch(url + type, {
            method: 'POST',
            body: JSON.stringify(userData)
        })
        .then((response) => response.json())
        .then((res) => {
            resolve(res);
        })
        .catch((error) => {
            reject(error);
        });
    });
}