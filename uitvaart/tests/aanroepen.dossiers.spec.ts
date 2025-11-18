import {getAxios} from './axios';
import {describe, expect, test} from "vitest";
import * as process from "node:process";

describe('ophalen van dossiers met queryparameters api', () => {
    test('Oproepen met kbonummer ', async () => {
        const axios = getAxios()
        const response = await axios.get(`/uitvaart/v2/dossiers?kbonummer=${process.env.KBONUMMER}`)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });
});

describe('ophalen van nog niet geclaimde dossiers', () => {

    test('Oproepen met rijksregisternummer', async () => {
        const axios = getAxios()
        const response = await axios.get(`/uitvaart/v1/overlijdens?rijksregisternummer=30612399749`)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Oproepen met dossiernummer', async () => {
        const axios = getAxios()
        const response = await axios.get('/uitvaart/v1/overlijdens?dossiernummer=20250109-002-r3pu')

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Oproepen met naam, niscode en datum overlijden', async () => {
        const axios = getAxios()
        const response = await axios.get('/uitvaart/v1/overlijdens?naam=Janssens&datumOverlijden=2024-10-10&niscode=24062')

        console.log(response.data);
        expect(response.status).toEqual(200);
    });
})

describe('ophalen van een dossier api', () => {
    test('Oproepen ', async () => {
        const axios = getAxios()
        const response = await axios.get(`/uitvaart/v1/dossiers/20250109-002-r3pu`)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });
})

describe('ophalen van documenten', () => {
    test('Oproepen van nationale akte', async () => {
        const axios = getAxios()
        const response = await axios.get(`/uitvaart/v1/dossiers/20250109-002-r3pu/documenten/NATIONALE_AKTE`)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });
})

describe('ophalen van transportview', () => {
    test('Oproepen van transportview', async () => {
        const axios = getAxios()
        const response = await axios.get(`/uitvaart/v1/dossiers/20250109-002-r3pu/transport-view`)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });
})

describe('Acties op dossier', () => {
    test('Verwijder recente acties', async () => {
        const axios = getAxios()
        const response = await axios.post(`/uitvaart/v1/dossiers/20250109-002-r3pu/verwijder-recente-acties`)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });
    test('Start op', async () => {
        const axios = getAxios()
        const response = await axios.post(`/uitvaart/v1/dossiers/20250109-002-r3pu/start-op`)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });
    test('heropen', async () => {
        const axios = getAxios()
        const response = await axios.post(`/uitvaart/v1/dossiers/20250109-002-r3pu/heropen`)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });
    test('behandeld', async () => {
        const axios = getAxios()
        const response = await axios.post(`/uitvaart/v1/dossiers/20250109-002-r3pu/behandeld`)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });
    test('annuleer', async () => {
        const axios = getAxios()
        const response = await axios.post(`/uitvaart/v1/dossiers/20250109-002-r3pu/annuleer`)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });
})


