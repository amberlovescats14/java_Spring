import React, { useEffect} from 'react'
import {Card} from '@material-ui/core'
import pink from '@material-ui/core/colors/pink'
const lightPink = pink[100]

const style = {
  card: {
    color: "white",
    background: lightPink,
    margin: '20px',
    padding: '10px'
  }
}

const Posts = (props) => {
  const {getPosts, postObject} = props
  useEffect(()=> {
    getPosts()
  }, [getPosts])
  return (
    <div>
      {postObject.posts.map((obj, i)=> (
        <Card key={i} style={style.card}>
          <h2>{obj.title}</h2>
          <p>{obj.description}</p>
        </Card>
      ))}
    </div>
  )
}

export default Posts
// useEffect(()=> {
//   let res = axios.get(`${proxy}/posts`)
//   setPosts(res.data)

//  }, [])
//  const [posts, setPosts] = useState([]);
