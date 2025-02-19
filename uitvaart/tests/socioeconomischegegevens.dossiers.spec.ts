import {getAxios} from './axios';
import {describe, expect, test} from "vitest";

import {JONGER_DAN_1_JAAR, OUDER_DAN_1_JAAR} from "./examples/socio.economische.gegevens";

const dossier = '20250109-002-r3pu';
const dossierJongerDan1Jaar = '20250110-001-a4af';

describe('Socio economische gegevens', () => {
    test('Ophalen gegevens', async () => {
        const axios = getAxios()
        const response = await axios.get(`/uitvaart/v1/dossiers/${dossier}/socio-economische-gegevens`)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Bewaren van socio economische gegevens', async () => {
        const axios = getAxios()

        const response = await axios.put(`/uitvaart/v1/dossiers/${dossier}/socio-economische-gegevens`, OUDER_DAN_1_JAAR)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Bewaren van socio economische gegevens jonger dan 1 jaar', async () => {
        const axios = getAxios()

        const response = await axios.put(`/uitvaart/v1/dossiers/${dossierJongerDan1Jaar}/socio-economische-gegevens`, JONGER_DAN_1_JAAR)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

})
