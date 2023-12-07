export const load = ({ fetch, params }) => {
    console.log(params);
    const folder_id = params.folder_id;



    return {
        folder_id: folder_id
    };
}