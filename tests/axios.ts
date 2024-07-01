import axios from "axios";
import https from "node:https";

export function getAxios() {
    return axios.create({
        httpsAgent: new https.Agent({
            rejectUnauthorized: false
        })
    });
}
