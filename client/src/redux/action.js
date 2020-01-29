import axios from 'axios'
export const getPosts = () => async (dispatch) => {
  try {
    const res = await axios.get('/posts')
    dispatch({
      type: `GET_POSTS`,
      payload: res.data
    })
  } catch (error) {
    console.log("get error");
    console.error(error.message)
  }
}