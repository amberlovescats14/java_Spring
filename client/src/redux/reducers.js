import { combineReducers } from 'redux'

const postInitialState = {
  posts : [],
  loading: false
}

export const getPosts = (state = postInitialState, action) => {
  const {payload, type} = action
  switch(type){
    case `GET_POSTS`:
    return {
      ...state,
      posts: payload,
      loading: false
    }
    default: return state
  }
}


export default combineReducers({
  postObject: getPosts
})