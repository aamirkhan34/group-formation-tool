CREATE TABLE Survey(  
   id BIGINT PRIMARY KEY AUTO_INCREMENT,   
   courseID BIGINT NOT NULL,   
   instructoreID bigint NOT NULL,   
   isPublished bit default 0, 
   FOREIGN KEY(courseID) REFERENCES Course(id),   
   FOREIGN KEY(instructoreID) REFERENCES User(id)
   );

CREATE TABLE SurveyQuestions(  
  surveyID bigint,
  QuestionID bigint,
   FOREIGN KEY(surveyID) REFERENCES Survey(id)
   ON DELETE CASCADE,
   FOREIGN KEY(QuestionID) REFERENCES Question(id)
      ON DELETE CASCADE
   );
