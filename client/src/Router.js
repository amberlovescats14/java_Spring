import React from 'react'
import {Switch, Route} from 'react-router-dom'
import Landing from './components/Landing'
import PostContainer from './containers/PostContainer'

const Router = () => {
  return (
    <Switch>
      <Route exact strict path='/' component={Landing}/>
      <Route exact strict path='/posts' component={PostContainer}/>
      
    </Switch>
  )
}

export default Router
