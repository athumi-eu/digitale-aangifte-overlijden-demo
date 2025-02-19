import {getAxios} from './axios';
import {describe, expect, test} from "vitest";

describe('oproepen van datadeling api', () => {
    test('Oproepen met enkel van ', async () => {
        const axios = getAxios()
        const response = await axios.get('/datadeling/v1/vaststellingen?van=2024-12-20T10:00:00')

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Oproepen met van en tot', async () => {
        const axios = getAxios()
        const response = await axios.get('/datadeling/v1/vaststellingen?van=2024-12-20T10:00:00&tot=2024-12-28T10:00:00')

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Oproepen met id', async () => {
        const axios = getAxios()
        const response = await axios.get('/datadeling/v1/vaststellingen/20250108-001')

        console.log(response.data);
        expect(response.status).toEqual(200);
    });
})
