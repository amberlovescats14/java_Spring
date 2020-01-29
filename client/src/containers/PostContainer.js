import Posts from '../components/posts/Posts'
import { connect } from 'react-redux'
import {getPosts} from '../redux/action'


const mapStateToProps = state => ({
  postObject: state.postObject
})

const mapDispatchToProps = dispatch => ({
  getPosts: () => dispatch(getPosts())
})

export default connect(mapStateToProps, mapDispatchToProps)(Posts)