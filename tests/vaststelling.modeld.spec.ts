import {describe, expect, test} from 'vitest';
import {getAxios} from './axios';

const TARGET = 'http://localhost:8090';
// const TARGET = 'https://dao.api.test-athumi.eu';

async function createVaststellingJongerDan1Jaar(vaststelling: any): Promise<any> {
    return getAxios()
        .post(TARGET + '/vaststelling/jonger-dan-1-jaar', vaststelling)
        .then((r) => r.data)
        .catch((e) => e.response.data);
}

describe('Aanmaken vaststelling jonger dan 1 jaar (Model D)', () => {

});
