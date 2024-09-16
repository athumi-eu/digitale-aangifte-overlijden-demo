import {describe, expect, test} from 'vitest';
import {getAxios} from './axios';
import {VERSLAG_BEEDIGD_ARTS} from "../src/examples/verslag-beedigd-arts/verslag-beedigd-arts";

async function createVerslagBeedigdArts(verslag: any): Promise<any> {
    return getAxios()
        .post('/verslag-beedigd-arts/v1', verslag)
        .then((r) => r.data)
        .catch((e) => e.response.data);
}

describe('Aanmaken verslag beëdigd arts', () => {

    test('succesvol', async () => {
        let response = await createVerslagBeedigdArts(VERSLAG_BEEDIGD_ARTS);

        console.log(response);
        expect(response.id).toBeTruthy();
    });

});
