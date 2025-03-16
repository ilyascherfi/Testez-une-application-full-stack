package com.openclassrooms.starterjwt.services;

import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class TeacherServiceTest {
  @Mock
  private TeacherRepository teacherRepository;

  @InjectMocks
  private TeacherService teacherService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void findAllTest(){
    List<Teacher> teachers = new ArrayList<>();
    when(teacherRepository.findAll()).thenReturn(teachers);
    teacherService.findAll();
    verify(teacherRepository, times(1)).findAll();
  }
  @Test

  public void findByIdTest(){
    Long id = new Long(1);
    Teacher teacher = new Teacher();
    Optional<Teacher> optTeacher = Optional.of(teacher);
    when(teacherRepository.findById(id)).thenReturn(optTeacher);
    teacherService.findById(id);
    verify(teacherRepository, times(1)).findById(id);
  }
}