import {getAxios} from './axios';
import {describe, expect, test} from "vitest";
import * as process from "node:process";
import {
    ANDER_CREMATORIUM,
    BEGRAAFPLAATS_ANDERE_LOCATIE, BESTEMMING_EN_AS_WETTELIJKE_PARTNER,
    CREMATORIUM_BELGIE,
    ENKEL_BEGRAAFPLAATS, LEVENLOOS_KIND, MET_AS_WETTELIJKE_PARTNER, PRIVATE_BEGRAVING
} from "./examples/inlichtenfiche.create";

const dossierOuderDan=  '20250109-002-r3pu';
const dossierDoodgeboorte=  '20250110-001-a4af';

describe('Inlichtingenfiche', () => {
    test('Ophalen inlichtingenfiche', async () => {
        const axios = getAxios()
        const response = await axios.get(`/uitvaart/v1/dossiers/${dossierOuderDan}/inlichtingenfiche`)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Bewaren van inlichtingenfiche begraafplaats', async () => {
        const axios = getAxios()
        const form = new FormData();
        form.set('inlichtingenficheUitvaart', new Blob([JSON.stringify(ENKEL_BEGRAAFPLAATS)], {
            type: 'application/json'
        }));
        const response = await axios.put(`/uitvaart/v1/dossiers/${dossierOuderDan}/inlichtingenfiche`,
            form)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Bewaren van inlichtingenfiche begraafplaats met andere locatie', async () => {
        const axios = getAxios()
        const form = new FormData();
        form.set('inlichtingenficheUitvaart', new Blob([JSON.stringify(BEGRAAFPLAATS_ANDERE_LOCATIE)], {
            type: 'application/json'
        }));
        const response = await axios.put(`/uitvaart/v1/dossiers/${dossierOuderDan}/inlichtingenfiche`,
            form)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Bewaren van inlichtingenfiche crematorium met andere locatie', async () => {
        const axios = getAxios()
        const form = new FormData();
        form.set('inlichtingenficheUitvaart', new Blob([JSON.stringify(ANDER_CREMATORIUM)], {
            type: 'application/json'
        }));
        const response = await axios.put(`/uitvaart/v1/dossiers/${dossierOuderDan}/inlichtingenfiche`,
            form)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Bewaren van inlichtingenfiche crematorium met crematorium belgie', async () => {
        const axios = getAxios()
        const form = new FormData();
        form.set('inlichtingenficheUitvaart', new Blob([JSON.stringify(CREMATORIUM_BELGIE)], {
            type: 'application/json'
        }));
        const response = await axios.put(`/uitvaart/v1/dossiers/${dossierOuderDan}/inlichtingenfiche`,
            form)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Bewaren van inlichtingenfiche crematorium met private begraving', async () => {
        const axios = getAxios()
        const form = new FormData();
        form.set('inlichtingenficheUitvaart', new Blob([JSON.stringify(PRIVATE_BEGRAVING)], {
            type: 'application/json'
        }));
        const response = await axios.put(`/uitvaart/v1/dossiers/${dossierOuderDan}/inlichtingenfiche`,
            form)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Bewaren van inlichtingenfiche crematorium met as wettelijke partner', async () => {
        const axios = getAxios()
        const form = new FormData();
        form.set('inlichtingenficheUitvaart', new Blob([JSON.stringify(MET_AS_WETTELIJKE_PARTNER)], {
            type: 'application/json'
        }));
        const response = await axios.put(`/uitvaart/v1/dossiers/${dossierOuderDan}/inlichtingenfiche`,
            form)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Bewaren van inlichtingenfiche crematorium met Levenloos kind', async () => {
        const axios = getAxios()
        const form = new FormData();
        form.set('inlichtingenficheUitvaart', new Blob([JSON.stringify(LEVENLOOS_KIND)], {
            type: 'application/json'
        }));
        const response = await axios.put(`/uitvaart/v1/dossiers/${dossierDoodgeboorte}/inlichtingenfiche`,
            form)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

    test('Bewaren van inlichtingenfiche crematorium met bestemming en as wettelijke partner', async () => {
        const axios = getAxios()
        const form = new FormData();
        form.set('inlichtingenficheUitvaart', new Blob([JSON.stringify(BESTEMMING_EN_AS_WETTELIJKE_PARTNER)], {
            type: 'application/json'
        }));
        form.set('VERZOEK_NABESTAANDE_AS_PARTNER', getDummyPDF())
        const response = await axios.put(`/uitvaart/v1/dossiers/${dossierOuderDan}/inlichtingenfiche`,
            form)

        console.log(response.data);
        expect(response.status).toEqual(200);
    });

})

function getDummyPDF() {
    const dataBase64 = 'VEhJUyBJUyBUSEUgQU5TV0VSCg==';
    const arrayBuffer = Uint8Array.from(dataBase64, (c) => c.charCodeAt(0));
    return new File([arrayBuffer], 'dummy.pdf', { type: 'application/pdf' });
}
