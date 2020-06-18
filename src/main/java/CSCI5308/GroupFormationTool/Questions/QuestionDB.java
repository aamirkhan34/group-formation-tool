package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDB implements IQuestionPersistence {
    @Override
    public List<Question> loadAllQuestionsByInstructor(long instructorId) {
        CallStoredProcedure proc = null;
        List<Question> questionList = new ArrayList<Question>();
        try {
            proc = new CallStoredProcedure("spFindQuestionsByUserID(?)");
            proc.setParameter(1, instructorId);
            ResultSet results = proc.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    User user = new User();
                    Question question = new Question();
                    question.setId(results.getInt(1));
                    user.setID(results.getLong(2));
                    question.setInstructor(user);
                    question.setType(results.getString(3));
                    question.setTitle(results.getString(4));
                    question.setText(results.getString(5));
                    question.setCreatedOn(results.getDate(6));
                    questionList.add(question);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return questionList;
    }

    @Override
    public Question loadQuestionById(long questionId) {
        ArrayList<MultipleChoiceOption> multipleChoiceOptionList = new ArrayList<>();
        CallStoredProcedure proc = null;
        Question question = new Question();
        try {
            proc = new CallStoredProcedure("spLoadQuestionById(?)");
            proc.setParameter(1,questionId);
            ResultSet resultSet = proc.executeWithResults();
            while (resultSet.next())
            {
                int type = resultSet.getInt(3);

                if (type == 2 || type == 3)
                {
                    multipleChoiceOptionList =  loadMultipleChoiceOptions(questionId);
                    question.setMultipleChoiceOption(multipleChoiceOptionList);
                }
                question.setTypeID(resultSet.getInt(3));
                question.setTitle(resultSet.getString(4));
                question.setText(resultSet.getString(5));
                question.setCreatedOn(resultSet.getDate(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return question;
    }

    private ArrayList<MultipleChoiceOption> loadMultipleChoiceOptions(long questionId){
        ArrayList<MultipleChoiceOption> multipleChoiceOptionList = new ArrayList<>();
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spLoadQuestionOptionsById(?)");
            proc.setParameter(1, questionId);
            ResultSet resultSet = proc.executeWithResults();
            while (resultSet.next()){
                MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
                multipleChoiceOption.setDisplayText(resultSet.getString(3));
                multipleChoiceOption.setOptionNumber(resultSet.getInt(4));
                multipleChoiceOptionList.add(multipleChoiceOption);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return multipleChoiceOptionList;
    }

    @Override
    public boolean createQuestion(Question question) {
        CallStoredProcedure proc = null;
        try {
            if (question.getTypeID() == 1 || question.getTypeID() == 4) {
                proc = new CallStoredProcedure("spCreateQuestion(?, ?, ?, ?)");
                proc.setParameter(1, question.getTitle());
                proc.setParameter(2, question.getText());
                proc.setParameter(3, question.getInstructor().getID());
                proc.setParameter(4, question.getTypeID());
                proc.execute();
                return true;

            } else if (question.getTypeID() == 2 || question.getTypeID() == 3) {
                proc = new CallStoredProcedure("spCreateQuestion(?, ?, ?, ?)");
                proc.setParameter(1, question.getTitle());
                proc.setParameter(2, question.getText());
                proc.setParameter(3, question.getInstructor().getID());
                proc.setParameter(4, question.getTypeID());
                ResultSet resultSet = proc.executeWithResults();
                int questionIdfromDB = 1;
                while (resultSet.next()) {
                    questionIdfromDB = resultSet.getInt(1);
                }
                System.out.println(questionIdfromDB);
                createMultipleQuestionOptions(questionIdfromDB, question.getMultipleChoiceOption());
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return false;
    }

    private boolean createMultipleQuestionOptions(long id, ArrayList<MultipleChoiceOption> options) {
        CallStoredProcedure proc = null;
        try {
            for(int i = 0; i< options.size();i++) {
                proc = new CallStoredProcedure("spCreateQuestionWithOptions(?, ?, ?)");
                proc.setParameter(1, id);
                proc.setParameter(2, options.get(i).getDisplayText());
                proc.setParameter(3, options.get(i).getOptionNumber());
                proc.execute();
            }
            return true;
        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
    }

    @Override
    public boolean deleteQuestionById(long questionId) {
        return false;
    }

    @Override
    public List<Question> loadAllQuestionTypes() {
        List<Question> questions = new ArrayList<Question>();
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spLoadAllQuestoinTypes()");
            ResultSet results = proc.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    int id = results.getInt(1);
                    String questionType = results.getString(2);
                    Question question = new Question();
                    question.setTypeID(id);
                    question.setType(questionType);
                    questions.add(question);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return questions;
    }
}
