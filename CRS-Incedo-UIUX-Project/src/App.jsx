import { useState } from 'react'
import './App.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { Student } from './components/Student/Student'
import { Course } from './components/Student/components/Course'
import { Professor } from './components/Professor/Professor'
import { Admin } from './components/Admin/Admin'
import { Pay } from './components/Student/components/Pay'
import 'bootstrap/dist/css/bootstrap.min.css'
import { Register as SRegister } from './components/Student/components/Register'
import { Grades } from './components/Professor/components/Grades'
import { Course as PCourse } from './components/Professor/components/Course'
import { Report } from './components/Admin/components/Report'
import { Professor as AProfessor } from './components/Admin/components/Professor'
import { Course as ACourse } from './components/Admin/components/Course'
import { Approval } from './components/Admin/components/Approval'
import { About } from './components/Home/components/About'
import { Home } from './components/Home/Home'
import { Password } from './components/Home/components/Password'
import { Register } from './components/Home/components/Register'


function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Router>
        <Routes>
          <Route path="/student/course" element={<Course />} />
          <Route path="/student/pay" element={<Pay />} />
          <Route path="/student/register" element={<SRegister />} />
          <Route path="/student" element={<Student />} />
          <Route path="/professor/grade" element={<Grades />} />
          <Route path="/professor/course" element={<PCourse />} />
          <Route path="/professor" element={<Professor />} />
          <Route path="/admin/report" element={<Report />} />
          <Route path="/admin/professor" element={<AProfessor />} />
          <Route path="/admin/course" element={<ACourse />} />
          <Route path="/admin/approve" element={<Approval />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/password" element={<Password />} />
          <Route path="/register" element={<Register />} />
          <Route path="/" element={<Home />} />
        </Routes>
      </Router>
    </>
  )
}

export default App
