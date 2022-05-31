package br.com.springdata.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.springdata.data.service.CargoService;
import br.com.springdata.data.service.FuncionarioService;
import br.com.springdata.data.service.UnidadeTrabalhoService;
import br.com.springdata.data.service.RelatoriosService;

@SpringBootApplication
public class DataApplication implements CommandLineRunner {
	
	private Boolean system = true;

	private final CargoService cargoService;

	private final RelatoriosService relatoriosService;
	
	private final FuncionarioService funcionarioService;

	private final UnidadeTrabalhoService unidadeTrabalhoService;
	
	public DataApplication(CargoService cargoService, 
			RelatoriosService relatoriosService, 
			FuncionarioService funcionarioService, 
			UnidadeTrabalhoService unidadeTrabalhoService) {
		this.cargoService = cargoService;
		this.relatoriosService = relatoriosService;
		this.funcionarioService = funcionarioService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual função deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade");
			System.out.println("4 - Relatorios");
			

			Integer function = scanner.nextInt();

			switch (function) {
				case 1:
					cargoService.inicial(scanner);
					break;
				case 2:
					funcionarioService.inicial(scanner);
					break;
				case 3:
					unidadeTrabalhoService.inicial(scanner);
					break;
				case 4:
					relatoriosService.inicial(scanner);
					break;
				default:
					System.out.println("Finalizando");
					system = false;
					break;
			}
		}
	}
}
