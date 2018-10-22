import { API_BASE_URL, ACCESS_TOKEN } from '../constants/constants';


const headers = () => {
    const h = new Headers();

    h.append('Content-Type', 'application/json');

    const session = {
        token: localStorage.getItem('token')
    };

    if (session.token){
        h.append('Authorization', session.token)
    };

    return h;
};


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

    const url = API_BASE_URL + '/customers/orders';
    const options = {method: 'POST', headers: headers(), body: JSON.stringify(order)};

    return fetch(new Request(url, options));
}


export function getDailyMenu() {


    const url = API_BASE_URL + '/customers/dailymenu';
    const options = { method: 'GET', headers: headers()};


    return fetch(new Request(url, options))

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