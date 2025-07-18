package homework_4; // 패키지 선언: 프로젝트의 논리적 구조를 정의하고 Java 클래스들을 그룹화하는 데 사용됨.

import javax.swing.*; // Java Swing 라이브러리의 모든 클래스를 포함하기 위해 사용. Swing은 GUI를 생성하기 위한 Java 표준 라이브러리.
import java.awt.*; // AWT(Abstract Window Toolkit) 라이브러리. GUI의 레이아웃 및 색상 등의 시각적 요소를 설정하는 데 사용.
import java.awt.event.*; // AWT 이벤트 처리 라이브러리. 사용자 상호작용(버튼 클릭, 키 입력 등)을 처리하는 데 사용.
import java.math.BigDecimal; // 고정 소수점 및 무한 정밀도 소수 연산을 위한 Java 클래스. 숫자 계산의 정확도를 보장한다.
import java.util.*; // Java 유틸리티 패키지. 컬렉션 클래스(Stack, List 등)를 사용하기 위해 포함.

public class Calculator { // Calculator 클래스 정의하며 Java에서 모든 프로그램의 기본 구성 요소인 클래스임.
    private JFrame frame; // JFrame은 Swing에서 제공하는 최상위 컨테이너로, 창을 생성하는 데 사용함.
    private JTextField display; // JTextField는 텍스트 입력 및 출력이 가능한 Swing 컴포넌트임. 계산식과 결과를 표시함.
    private StringBuilder currentExpression; // StringBuilder는 문자열을 동적으로 생성 및 수정하고 현재 수식을 저장하는 데 사용함.

    public Calculator() { // Calculator 클래스의 생성자이고 객체 초기화를 위해 호출됨.
        currentExpression = new StringBuilder(); // 수식을 저장하기 위해 StringBuilder 객체를 초기화함(계산식을 초기화).

        frame = new JFrame("Calculator"); // "Calculator"라는 제목을 가진 새로운 JFrame 객체를 생성함.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        // JFrame 닫기 버튼을 눌렀을 때, 프로그램이 종료하도록 설정. 
        // EXIT_ON_CLOSE: 프로그램의 모든 리소스를 정리하고 JVM을 종료하는 것임.

        frame.setSize(300, 400); // JFrame의 크기를 너비 300픽셀, 높이 400픽셀로 설정.
        frame.setLayout(new BorderLayout()); 
        // JFrame에 레이아웃 매니저로 BorderLayout를 설정(사용)함.
        // BorderLayout은 컨테이너를 동서남북, 중앙(CENTER)으로 구분함.

        setupDisplay(); // 디스플레이 컴포넌트를 초기화하는 메서드를 호출하는 것.
        setupButtons(); // 버튼 컴포넌트를 초기화하는 메서드를 호출하는 것.

        frame.setFocusable(true); 
        // 프레임이 키보드 이벤트를 받을 수 있도록 포커스를 설정.
        // 포커스가 없으면-> 키 입력이 제대로 전달되지 않는다.

        frame.setVisible(true); 
        // JFrame을 화면에 표시-> true로 설정하면 프레임이 사용자에게 렌더링됨.
    }

    // 디스플레이 초기화 메서드 -> 계산기의 출력 창을 설정한다.
    private void setupDisplay() {
        display = new JTextField(); //계산 창 설정--
        // JTextField는 텍스트를 입력하거나 표시할 수 있는 컴포넌트임.
        // 이 경우에는 사용자 입력 대신 계산 결과를 보여주기 위해 사용됨.
        display.setFont(new Font("Arial", Font.BOLD, 18)); 
        // 디스플레이의 텍스트 폰트를 설정. Arial 폰트를 굵게, 크기 18로 설정함.
        display.setHorizontalAlignment(JTextField.RIGHT); 
        // 텍스트 정렬을 오른쪽으로 설정. 숫자는 일반적으로 오른쪽 정렬로 표시함.
        display.setEditable(false); 
        // 사용자가 텍스트 필드에 직접 입력하지 못하도록 설정함.
        
        
        JPanel displayPanel = new JPanel(); // 버튼 패널 생성 --
        // JPanel은 Swing 컨테이너 클래스이며 디스플레이 컴포넌트를 담을 패널을 생성함.
        displayPanel.setLayout(new BorderLayout()); 
        // 패널에 BorderLayout을 설정하고 텍스트 필드를 중앙에 위치시키기 위해 사용함.
        displayPanel.setPreferredSize(new Dimension(300, 50)); 
        // 패널 크기를 300x50 픽셀로 설정하는 것이고 디스플레이 영역의 고정 크기 지정을 함.
        display.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); 
        // JTextField 주위에 두께 2의 검은색 테두리를 설정하는 것임.
        // BorderFactory는 테두리를 생성하는 유틸리티 클래스임.
        displayPanel.add(display, BorderLayout.CENTER); 
        // 디스플레이(JTextField)를 패널의 중앙(CENTER)에 추가함.
        frame.add(displayPanel, BorderLayout.NORTH); 
        // KeyAdapter는 KeyListener 인터페이스의 기본 구현을 제공하는 추상 클래스이며 이를 상속받아 필요한 메서드만 오버라이딩함.
        // 디스플레이 패널을 JFrame의 북쪽에 추가, 프레임 상단에 추가하는 것임.
        display.addKeyListener(new KeyAdapter() { 
            // KeyAdapter 클래스는 KeyListener 인터페이스의 추상 메서드를 기본 구현으로 제공함.
            // 이를 확장하여 키 입력 이벤트를 처리함.
            @Override
            public void keyPressed(KeyEvent e) { 
                // KeyEvent 발생 시 호출되는 메서드, 키가 눌렸을 때 호출되는 메서드로 KeyListener 인터페이스의 추상 메서드임.
                // 사용자가 키보드를 눌렀을 때 발생하는 이벤트를 처리이며 키 입력 처리 메서드 호출임.

                handleKeyPress(e); 
                // 사용자가 입력한 키를 처리하는 별도의 메서드를 호출함.
            }
        });
    }

    // 버튼 초기화 메서드-> 숫자 및 연산 버튼을 설정한다.
    private void setupButtons() {
        JPanel buttonPanel = new JPanel(); 
        // JPanel을 생성하여 버튼들을 담는 컨테이너로 사용함.
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5)); 
        // GridLayout 설정을 4행 4열의 격자로 버튼을 정렬하고 행과 열 사이에 5픽셀의 간격을 추가한다는 것임.

        String[] buttons = {
                "7", "8", "9", "÷", "4", "5", "6", "*", 
                "1", "2", "3", "-", "0", ".", "=", "+"
        }; 
        // 계산기에 표시될 버튼 텍스트 배열임.

        for (String text : buttons) { 
            // 배열을 순회하여 각 버튼을 생성하는 것.
            JButton button = new JButton(text); 
            // 버튼 생성-> 텍스트는 배열의 현재 값으로 설정함.
            button.setFont(new Font("Arial", Font.BOLD, 16)); 
            // 버튼 텍스트의 폰트를 Arial, 굵게, 크기 16으로 설정함.
            button.setPreferredSize(new Dimension(50, 50)); 
            // 버튼의 기본 크기를 설정함.
            button.addActionListener(new ButtonClickListener()); 
            // 버튼 클릭 이벤트 리스너 등록.
            // 버튼을 눌렀을 때 ButtonClickListener 클래스의 메서드가 호출됨.
            buttonPanel.add(button); 
            // 버튼을 패널에 추가함.
        }

        frame.add(buttonPanel, BorderLayout.CENTER); 
        // 버튼 패널을 JFrame의 중앙(CENTER)에 추가.
    }

    // 키보드 입력 처리 메서드-> 키 입력을 받아 계산식으로 변환하는 것이다.
    private void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode(); 
        // 사용자가 누른 키의 코드 값을 가져옴. 
        // KeyEvent 클래스에서 키 코드는 정수 값으로 정의되어 있음.
        char keyChar = e.getKeyChar(); 
        // 키보드 입력으로 해당 키의 문자 값을 가져옴.
        // 예를 들자면 숫자 키 '1'을 누르면 '1'이라는 문자가 반환되는 것.
        // 숫자 키나 연산자 키를 입력했는지 확인해야함.
        if (Character.isDigit(keyChar) || "÷*+-.".indexOf(keyChar) != -1) { 
            // Character.isDigit()-> 입력이 숫자(0~9)인지 확인함.
            // "÷*+-.".indexOf(keyChar)-> 연산자나 소수점(.) 여부를 확인함.
            currentExpression.append(keyChar); 
            // 입력된 키를 수식에 추가함.
            // 예를 들면 현재 수식이 "12"이고 '+'를 입력하면, "12+" 이런 식으로 된다는 것임.

            display.setText(currentExpression.toString()); 
            // 현재 수식을 디스플레이에 업데이트함.
        } else if (keyCode == KeyEvent.VK_BACK_SPACE) { 
            // 백스페이스 키를 누르면 실행되며 코드에서 KeyEvent.VK_BACK_SPACE는 백스페이스 키의 코드 값을 뜻함.

            if (currentExpression.length() > 0) { 
                // 수식이 비어있지 않으면 마지막 문자 제거함.
                currentExpression.deleteCharAt(currentExpression.length() - 1); 
                // deleteCharAt()-> 특정 위치의 문자를 삭제함.
            }

            display.setText(currentExpression.toString()); 
            // 업데이트된 수식을 디스플레이에 반영함.
        } else if (keyCode == KeyEvent.VK_ENTER || keyChar == '=') { 
            // 엔터 키 또는 '=' 키를 누르면 실행함.
            calculate(); 
            // 계산을 수행하는 메서드 호출임.
        }
    }

    // 이 클래스는 버튼 클릭 이벤트를 처리하기 위한 내부 클래스이며 사용자가 버튼을 클릭하면 호출이 된다.
    private class ButtonClickListener implements ActionListener { // 연산자 입력(버튼 클릭 이벤트)을 처리하는 내부 클래스가 됨.
        // ActionListener는 인터페이스로, 하나의 추상 메서드 actionPerformed()를 반드시 구현해야 함.
        // 이 인터페이스를 구현함으로써 버튼 클릭 이벤트를 처리할 수 있음.

        @Override
        public void actionPerformed(ActionEvent e) {
        	// 버튼이 클릭되었을 때 호출되는 메서드로 ActionListener 인터페이스의 추상 메서드가 됨.
            // ActionEvent 객체 e는 어떤 버튼이 클릭되었는지, 어떤 액션 커맨드인지 등의 정보를 포함함.

            String command = e.getActionCommand();
            // e.getActionCommand()-> 클릭된 버튼의 텍스트(또는 액션 커맨드)를 반환함.
            // 예를 들어서 "7" 버튼을 클릭한다면 "7"이라는 문자열 반환됨, 사용자가 클릭한 버튼의 텍스트 값을 가지고 옴.

            if ("=".equals(command)) {
                // "equals()" 메서드를 사용하여 클릭된 버튼의 텍스트가 "="인지 확인함.
                // 문자열 비교는 항상 "equals()"를 사용해야 함(== 사용 시 객체 참조를 비교하게 됨).
                
                calculate(); 
                // 계산을 수행하는 메서드를 호출함.
                // 버튼 "="는 계산을 실행하도록 연결되어 있음.
            } else {
                // "=" 버튼이 아닌 경우 실행됨.
                // 예를 들어, "1", "+", "5" 등의 버튼이 여기에 해당함.
                
                currentExpression.append(command);
                // append()-> 현재 수식(StringBuilder 객체)에 클릭된 버튼의 텍스트를 추가하는 것임.
                // 예를 들어서 기존 수식이 "12"이면 "+" 버튼을 누르면 "12+"가 되는 것임.

                display.setText(currentExpression.toString());
                // setText()-> 디스플레이에 현재 수식을 문자열 형태로 출력함.
                // 디스플레이는 사용자가 입력한 수식을 실시간으로 보여주는 역할을 함.
            }
        }
    }

    // 계산을 실제로 수행하는 핵심 메서드이며 현재 수식을 계산하고 결과를 표시한다.
    private void calculate() {
        try {
            // try-catch 블록을 사용하여 예외 처리함.
            // 예외 처리란 프로그램 실행 중 발생할 수 있는 오류를 대비하는 것임.
            // 여기서는 "잘못된 수식 입력" 또는 "0으로 나누기" 같은 오류를 처리함.
        	
            String expression = currentExpression.toString();
            // StringBuilder 객체를 문자열(String)으로 변환하여 계산식으로 사용함.
            // 현재 입력된 계산식을 가져오는 것. 예-> "12+5" 이런식으로.
            
            expression = expression.replace("÷", "/");
            // replace()-> 문자열의 특정 문자를 다른 문자로 바꾸는 것.
            // 디스플레이에는 "÷"로 표시되지만, 실제 계산에서는 "/"로 계산해야하므로 변환함.
            
            Stack<BigDecimal> numbers = new Stack<>();
            // 숫자를 저장할 스택 생성이고 스택(Stack)은 LIFO(Last In First Out) 구조를 가진 자료구조임.
            // 즉, 마지막에 추가된 값이 가장 먼저 처리됨.
            // 예를 들어서 1 -> 2 -> 3 순으로 추가하면, 연산 시 3 -> 2 -> 1 순으로 꺼냄.

            Stack<Character> operators = new Stack<>();
            // 연산자를 저장할 스택 생성함.
            // 숫자와 마찬가지로 연산자도 후입선출(LIFO) 구조를 사용해 우선순위 계산을 효율적으로 처리.

            StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/", true);
            // StringTokenizer은 문자열을 특정 구분자로 나누는 클래스임.
            // 두 번째 매개변수는 구분자(여기서는 "+-*/")를 기준으로 식을 나눔.
            // "true"를 추가하여 구분자도 토큰에 포함하도록 설정함.
            // 예를 들어서 "12+5"를 토큰화하면 ["12", "+", "5"]이런식으로 나타남.

            while (tokenizer.hasMoreTokens()) {
                // hasMoreTokens()-> 더 이상 처리할 토큰이 없을 때까지 반복, 토큰이 남아있는 동안 반복함.
                String token = tokenizer.nextToken();
                // nextToken()-> 다음 토큰을 반환하고 다음 토큰을 가지고 옴.

                if (isNumeric(token)) {
                    // isNumeric()-> 해당 토큰이 숫자인지 확인하는 것.
                    numbers.push(new BigDecimal(token));
                    // 숫자인 경우 BigDecimal로 변환하여 숫자 스택에 추가함.
                    // BigDecimal-> Java에서 숫자의 정밀한 계산을 위해 사용하는 클래스임.
                } else {
                    // 연산자일 경우 실행한다.
                	
                    while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token.charAt(0))) {
                        // 현재 연산자와 스택의 맨 위 연산자 우선순위 비교하고 스택의 맨 위 연산자의 우선순위가 현재 연산자보다 높거나 같을 때까지 반복한다는 것.

                        BigDecimal b = numbers.pop(); // 스택에서 두 번째 피연산자 꺼냄.
                        BigDecimal a = numbers.pop(); // 스택에서 첫 번째 피연산자 꺼냄.
                        char op = operators.pop();   // 스택에서 연산자 꺼냄.

                        numbers.push(performOperation(a, b, op));
                        // performOperation()-> 꺼낸 숫자와 연산자를 사용해 계산 수행하는 것.
                        // 연산 수행 후 결과를 숫자 스택에 추가함.
                    }

                    operators.push(token.charAt(0));
                    // 현재 연산자를 연산자 스택에 추가함.
                }
            }

            while (!operators.isEmpty()) {
                // 모든 연산자가 처리될 때까지 반복함.
                BigDecimal b = numbers.pop(); // 두 번째 피연산자 꺼냄.
                BigDecimal a = numbers.pop(); // 첫 번째 피연산자 꺼냄.
                char op = operators.pop();   // 연산자 꺼냄.

                numbers.push(performOperation(a, b, op));
                // 연산 결과를 숫자 스택에 추가.
            }

            BigDecimal result = numbers.pop();
            // 최종 결과값을 가지고오며 스택에 남아있는 유일한 숫자가 최종 결과가 됨.

            display.setText(result.toString());
            // 결과를 디스플레이에 출력하여 나타냄.

            currentExpression.setLength(0);
            // 계산 완료 후 현재 수식을 초기화하여 새 입력을 받을 준비를 함.
        } catch (Exception ex) {
            // 계산 중 오류 발생 시 실행됨.
            display.setText("Error");
            // 디스플레이에 "Error" 메시지를 표시하는 것.

            currentExpression.setLength(0);
            // 수식을 초기화하여 오류 상태를 정리함.
        }
    }

    // 연산자 우선순위를 반환하는 메서드이다.
    private int precedence(char operator) { 
        // switch문으로 연산자 우선순위를 반환.
        // "*"와 "/"의 우선순위는 2로 높고,
        // "+"와 "-"의 우선순위는 1로 낮음.
    	
        switch (operator) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            default: return -1;
        }
    }

    private BigDecimal performOperation(BigDecimal a, BigDecimal b, char operator) {
        // 두 숫자(a, b)와 연산자(operator)를 사용해 계산을 수행하는 메서드이고 연산결과를BigDecimal 객체로 반환함.
        // BigDecimal을 사용한 이유: 부동소수점 연산의 정확성을 보장하기 위함.
        // a-> 첫 번째 피연산자이고 b-> 두 번째 피연산자
        // operator: 연산을 나타내는 문자('+', '-', '*', '/')

        switch (operator) {
            // switch문은 연산자(operator)의 종류에 따라 다른 계산을 수행하게 됨.

            case '+':
                return a.add(b);
                // BigDecimal.add()-> 두 BigDecimal 객체의 합을 계산함.
                // a가 10이고 b가 5일 때, 결과는 15.(덧셈이라고 생각하면 됨)

            case '-':
                return a.subtract(b);
                // BigDecimal.subtract()-> 두 BigDecimal 객체의 차를 계산.
                // a가 10이고 b가 5일 때, 결과는 5.(뺄셈이라고 생각하면 됨)

            case '*':
                return a.multiply(b);
                // BigDecimal.multiply()-> 두 BigDecimal 객체의 곱을 계산.
                // 예: a가 10이고 b가 5일 때, 결과는 50(곱셈이라 생각하면 됨)

            case '/':
                if (b.compareTo(BigDecimal.ZERO) == 0) {
                    // compareTo()-> 두 BigDecimal 값을 비교함.
                    // b.compareTo(BigDecimal.ZERO)가 0이면 b가 0이라는 뜻임.
                	 // 0으로 나누는 경우 ArithmeticException 발생함.
                    throw new ArithmeticException("Division by zero");
                    // ArithmeticException-> 산술 연산에서 오류가 발생했을 때 던지는 예외임.
                    // 여기서는 0으로 나누는 경우 발생함.

                }
                return a.divide(b, 10, BigDecimal.ROUND_HALF_UP);
                // BigDecimal.divide()-> 두 BigDecimal 객체의 나눗셈을 수행함.
                // 두 번째 매개변수(10)-> 소수점 아래 10자리까지 정확히 계산함.
                // 세 번째 매개변수(BigDecimal.ROUND_HALF_UP)-> 반올림 방식 지정임.
                // ROUND_HALF_UP-> 소수점 아래 자릿수가 지정된 자리의 값에 따라 반올림함.

            default:
                throw new IllegalArgumentException("Invalid operator");
                // default-> 위에서 처리되지 않은 연산자(예: '%', '^')인 경우 실행함.
                // IllegalArgumentException-> 잘못된 인자를 받았을 때 던지는 예외임.
        }
    }


    // 문자열이 숫자인지 확인하는 유틸리티 메서드이다.
    private boolean isNumeric(String str) {
        // 문자열(str)이 숫자인지 확인하는 메서드임.
        // 숫자일 경우 true, 아니면 false 반환함.
        // str은 검사할 문자열을 뜻함.

        try {
            new BigDecimal(str);
            // 문자열을 BigDecimal 객체로 변환 시도하고,,,
            // 변환이 성공하면 해당 문자열은 숫자로 간주함.
            return true; // 숫자인 경우 true를 반환함.
        } catch (NumberFormatException e) {
            // NumberFormatException-> 문자열이 숫자로 변환할 수 없는 경우 발생하는 예외임.
            return false; // 숫자가 아닌 경우 false를 반환함.
        }
    }

    public static void main(String[] args) {
        // 프로그램의 진입점(Entry Point)이며 Java 프로그램은 항상 main 메서드에서 실행을 시작함.

        new Calculator();
        // Calculator 클래스의 인스턴스를 생성, 객체를 생성하여 프로그램 시작이됨.
        // 생성자를 호출하면 GUI(그래픽 사용자 인터페이스)가 생성되고, 이벤트 리스너 설정이나 초기화 작업이 자동으로 진행됨.
        }
}

