import axios from "axios";
import * as https from "node:https";
import 'dotenv/config'
import * as process from "node:process";


const TARGET = process.env.TARGET;


let authToken = await axios.post(process.env.TOKEN_URL, {
        grant_type: 'client_credentials',
        scope: ['elys_depzorg'],
    }, {
        headers: {
            'Authorization': `Basic ${process.env.BASIC_CLIENT}`,
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
    .then((response => response.data));

export  function getAxios() {
    return axios.create({
        httpsAgent: new https.Agent({
            rejectUnauthorized: false
        }),
        baseURL: TARGET,
        headers: {
            'Authorization': `Bearer ${authToken.access_token}`,
            'x-scopes': 'elys_depzorg'
        }
    });
}
