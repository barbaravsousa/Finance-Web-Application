import { useHistory } from 'react-router-dom';
import '../css/GoToPreviousPath.css';
import back from '../images/menu-back.png'

function Home() {
  const history = useHistory();  
  return <button className ="previous" onClick={() => history.goBack()}> <img src={back} /> </button>
}
export default Home;