package br.com.brenoryan.todolist.model.conf;

import br.com.brenoryan.todolist.model.entity.Task;
import br.com.brenoryan.todolist.model.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class Agendador {

    @Autowired
    private TaskRepository taskRepository;

    @Scheduled(fixedRate = 60000)
    private void verifyStatus() throws Exception {

        Thread thread = new Thread(() -> {
            try {
                List<Task> taskList = taskRepository.findTaskByStatus("CRIADO");
                LocalDateTime dataAtual = LocalDateTime.now();
                for (Task task : taskList) {
                    if (task.getEndAt().isBefore(dataAtual)) {
                        task.setStatus("FINALIZADA");
                        taskRepository.save(task);
                    }
                }
                System.out.println("Executou a tarefa");
            } catch (Exception e) {
                System.err.println("Ocorreu um erro ao tentar alterar o status da task: " + e.getMessage());
            }
        });
        thread.start();
    }
}
