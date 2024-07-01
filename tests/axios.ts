import axios from "axios";
import https from "node:https";

const TARGET = 'http://localhost:8090';
// const TARGET = 'https://dao.api.test-athumi.eu';


export function getAxios() {
    return axios.create({
        httpsAgent: new https.Agent({
            rejectUnauthorized: false
        }),
        baseURL: TARGET
    });
}
