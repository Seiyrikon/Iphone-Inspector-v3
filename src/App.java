import components.frame.MainFrame;

public class App {
    public static void main(String[] args) throws Exception {
        new MainFrame();

        // SwingController controller = new SwingController();

        // SwingViewBuilder factory = new SwingViewBuilder(controller);

        // // Build ONLY the document viewer panel
        // JPanel viewerPanel = factory.buildViewerPanel();
        
        // JFrame frame = new JFrame("PDF Viewer");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setLayout(new BorderLayout());
        // frame.add(viewerPanel, BorderLayout.CENTER);

        // frame.setSize(800, 700);
        // frame.setVisible(true);

        // controller.openDocument("sample_converted.pdf");
    }
}
