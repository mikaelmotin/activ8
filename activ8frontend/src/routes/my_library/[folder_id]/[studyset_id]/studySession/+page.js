export const load = ({ fetch, params }) => {
    console.log(params);
    const studyset_id = params.studyset_id;

    return {
        studyset_id: studyset_id,
    }};