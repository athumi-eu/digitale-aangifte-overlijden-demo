import {describe, expect, test} from 'vitest';
import {getAxios} from './axios';
import {VASTSTELLING} from "../src/examples/modeld/vaststelling.modeld";
import {VASTSTELLING_DOODGEBOORTE} from "../src/examples/modeld/vaststelling.modeld.doodgeboorte";

async function createVaststellingJongerDan1Jaar(vaststelling: any): Promise<any> {
    return getAxios()
        .post('/vaststelling/v1/jonger-dan-1-jaar', vaststelling)
        .then((r) => r.data)
        .catch((e) => e.response.data);
}

describe('Aanmaken vaststelling jonger dan 1 jaar (Model D)', () => {

    test('succesvol', async () => {
        let response = await createVaststellingJongerDan1Jaar(VASTSTELLING);

        console.log(response);
        expect(response.id).toBeTruthy();
    });

    test('doodgeboorte - succesvol', async () => {
        let response = await createVaststellingJongerDan1Jaar(VASTSTELLING_DOODGEBOORTE);

        console.log(response);
        expect(response.id).toBeTruthy();
    });

});
