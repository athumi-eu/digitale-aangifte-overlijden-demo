import {describe, expect, test} from 'vitest';
import {VASTSTELLING} from '../src/examples/modelc/vaststelling';
import {getAxios} from './axios';
import {VASTSTELLING_ERROR_CODELIJST} from '../src/examples/modelc/vaststelling-error-codelijst';
import {VASTSTELLING_ERROR_VERPLICHT_VELD} from "../src/examples/modelc/vaststelling-error-verplicht-veld";

const TARGET = 'http://localhost:8090';
// const TARGET = 'https://dao.api.test-athumi.eu';

async function createVaststellingOuderDan1Jaar(vaststelling: any): Promise<any> {
    return getAxios()
        .post(TARGET + '/vaststelling/ouder-dan-1-jaar', vaststelling)
        .then((r) => r.data)
        .catch((e) => e.response.data);
}

describe('Aanmaken vaststelling ouder dan 1 jaar (Model C)', () => {

    test('succesvol', async () => {
        let response = await createVaststellingOuderDan1Jaar(VASTSTELLING);

        console.log(response);
        expect(response.id).toBeTruthy();
    });

    test('codelijst error', async () => {
        let response = await createVaststellingOuderDan1Jaar(VASTSTELLING_ERROR_CODELIJST);

        console.log(response);
        expect(response.status).toEqual(400);
        expect(response.title).toEqual('Validatie mislukt');
        expect(response.detail).toEqual('Controlearts not in collection [Huisarts, ArtsVanVaststelling]');
    });

    test('verplicht veld error', async () => {
        let response = await createVaststellingOuderDan1Jaar(VASTSTELLING_ERROR_VERPLICHT_VELD);

        console.log(response);
        expect(response.status).toEqual(400);
        expect(response.title).toEqual('Validatie mislukt');
        expect(response.detail).toContain('Constraint ONGEVAL_OMSTANDIGHEDEN_REQUIRED violated by: null is null');
    });

});
