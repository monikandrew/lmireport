package it.businesslogic.ireport.plugin.templatemanager;

import it.businesslogic.ireport.gui.MainFrame;
import it.businesslogic.ireport.gui.listview.ScrollableList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

import org.openide.util.Exceptions;
import org.openide.util.Mutex;

/**
 *
 * @author gtoffoli
 */
public class TemplatesPanel extends javax.swing.JPanel implements Runnable {

    public static final String PROP_SELECTED_TEMPLATE = "selectedTemplate";
    TemplateRenderer renderer = null;
    private boolean fullPageView = false;


    List<TemplateCategory> categories = new ArrayList<TemplateCategory>();
    List<TemplateDescriptor> templates = new ArrayList<TemplateDescriptor>();

    /** Creates new form TemplatesPanel */
    public TemplatesPanel() {
        initComponents();
        jList1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        renderer = new TemplateRenderer();
        jList1.setCellRenderer(renderer);
        jList1.setModel(new DefaultListModel());
        for (int i=0; i<40; ++i)
        {
            ((DefaultListModel)jList1.getModel()).addElement(""+i);
        }
        jScrollPane1.getViewport().setBackground( Color.WHITE );  //new Color(37,37,37)

        //jList2.setCellRenderer(new TemplateCategoriesListCellRenderer());
        //jList2.setModel(new DefaultListModel());

        //updateCategories();
        //jList2.setSelectedIndex(0);
        clearPreview();
        new Thread(this).start();


    }

    public TemplateDescriptor getSelectedTamplate()
    {
        return (TemplateDescriptor)jList1.getSelectedValue();
    }

   /* public void setSelectedTamplate(FileObject obj)
    {
        File f = FileUtil.toFile(obj);
        for (int i=0; i<jList1.getModel().getSize(); ++i)
        {
            TemplateDescriptor td = (TemplateDescriptor)jList1.getModel().getElementAt(i);

            if (f.equals(td.getFile()))
            {
                jList1.setSelectedIndex(i);
                return;
            }
        }
    }*/

    public void setIconsSize(int size)
    {
        setIconsSize(size, false, true);
    }

    public int getIconsSize()
    {

        return renderer.getIconSize();
    }

    private void setIconsSize(int size, boolean fullPage, boolean ensureVisible)
    {
        this.fullPageView = fullPage;
        renderer.setIconSize(size);

        if (ensureVisible)
        {
            Mutex.EVENT.readAccess(new Runnable() {

                public void run() {
                    jList1.ensureIndexIsVisible(jList1.getSelectedIndex());

                }
            });
        }

        jList1.updateUI();
    }

    public TemplateCategory addCategory(String category, String subCategory)
    {
        TemplateCategory c = new TemplateCategory();
        c.setCategory(category);
        c.setSubCategory(subCategory);

        if (!categories.contains(c))
        {
            categories.add(c);
        }
        else
        {
            c = categories.get(categories.indexOf(c));
        }

        Mutex.EVENT.readAccess(new Runnable() {

            public void run() {
                updateCategories();
            }
        });


        return c;
    }


    public void addTemplate(final TemplateDescriptor td)
    {
        if (!templates.contains(td))
        {
            templates.add(td);
            Mutex.EVENT.readAccess(new Runnable() {

            public void run() {
                DefaultListModel model = (DefaultListModel) jList1.getModel();
                if (model.contains(td)) return;
                model.addElement(td);
                if (model.size() == 1)
                {
                    jList1.setSelectedIndex(0);
                }
            }
            });
        }




    }


    public void clearPreview()
    {
        Mutex.EVENT.readAccess(new Runnable() {

                public void run() {
                    DefaultListModel model = (DefaultListModel) jList1.getModel();
                    model.removeAllElements();
                    jList1.updateUI();
                }
            });
    }

    public void updatePreviews()
    {
        Mutex.EVENT.readAccess(new Runnable() {

                public void run() {
                    DefaultListModel model = (DefaultListModel) jList1.getModel();
                    model.removeAllElements();



            //        Object obj = jList2.getSelectedValue();
            //        String category = "";
            //        String subCategory = null;
            //        if (obj instanceof TemplateCategory)
            //        {
            //            category = ((TemplateCategory)obj).getCategory();
            //            subCategory = ((TemplateCategory)obj).getSubCategory();
            //        }
            //        else
            //        {
            //            category = "" +obj;
            //        }

                    for (TemplateDescriptor td : templates)
                    {
                        //if ( category.equals(TemplateCategory.CATEGORY_ALL_REPORTS) ||
                        //     ( td.getCategory().getCategory().equals(category) &&
                        //       (subCategory == null || subCategory.equals(td.getCategory().getSubCategory())) ))
                        //{
                        model.addElement(td);
                        //}
                    }

                    if (model.getSize() > 0)
                    {
                        jList1.setSelectedIndex(0);
                    }
                    jList1.updateUI();
                }
            });


    }

    public void updateCategories()
    {
        
        Object selectedCategory = jList1.getSelectedValue();

        TemplateCategory[] catArray = categories.toArray(new TemplateCategory[categories.size()]);
        Arrays.sort(catArray);
        categories = new ArrayList( Arrays.asList(catArray));

        DefaultListModel model = (DefaultListModel)jList1.getModel();
        model.removeAllElements();
        String lastCategory = TemplateCategory.CATEGORY_ALL_REPORTS;
        model.addElement(lastCategory);

        for (TemplateCategory cat : categories)
        {
            if (!lastCategory.equals(cat.getCategory()))
            {
                model.addElement(cat.getCategory());
                lastCategory = cat.getCategory();
            }
            if (cat.getSubCategory().length() > 0)
            {
                model.addElement(cat);
            }
        }

        jList1.setSelectedValue(selectedCategory, true);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new ScrollableList();

        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(37, 37, 37));
        jScrollPane1.setBorder(null);

        jList1.setForeground(new java.awt.Color(51, 51, 51));
        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setOpaque(false);
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jList1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jList1ComponentResized(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {
        firePropertyChange(PROP_SELECTED_TEMPLATE, jList1.getSelectedValue(), jList1.getSelectedValue());
    }

    private void jList1ComponentResized(java.awt.event.ComponentEvent evt) {
        if (isFullPageView())
        {
            int showingIndex = jList1.getSelectedIndex();
            if (showingIndex < 0 ||
                showingIndex < jList1.getFirstVisibleIndex() ||
                showingIndex > jList1.getLastVisibleIndex())
            {
                showingIndex = jList1.getFirstVisibleIndex();
            }

            final int ensureIndexVisible = showingIndex;
            setIconsSize((int)(this.getSize().getHeight()/1.2), true, false);
            Mutex.EVENT.readAccess(new Runnable() {

                public void run() {
                    jList1.ensureIndexIsVisible(0);
                    if (ensureIndexVisible != 0)
                    {
                        jList1.ensureIndexIsVisible(ensureIndexVisible);
                    }
                }
            });

        }
    }

    public JList getList()
    {
        return jList1;
    }

    // Variables declaration - do not modify
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration

    public void run() {

        // Load the templates from the templates directory inside the ireport module...
        File templatesDirectory = new File(MainFrame.IREPORT_TMP_TEMPLATE_DIR);
        if (templatesDirectory != null && templatesDirectory.exists())
        {
            loadTemplatesFromFile(templatesDirectory);
        }

        // Load all the templates from the templates directories (is set...)
        String pathsString = MainFrame.IREPORT_TMP_TEMPLATE_DIR;  //LIMAO :..

        // All the paths are separated by an end line...
        if (pathsString.length() > 0)
        {
            String[] paths = pathsString.split("\\n");
            for (int i=0; i<paths.length; ++i)
            {
                File f = new File(paths[i]);
                loadTemplatesFromFile(f);
            }
        }


        if (((DefaultListModel)jList1.getModel()).getSize() > 0)
        {
            Mutex.EVENT.readAccess(new Runnable() {

                public void run() {
                    jList1.setSelectedIndex(0);
                }
            });
        }

    }


    public void loadTemplatesFromFile(File file)
    {
        if (file == null || !file.exists()) return;

        if (file.isDirectory())
        {
            loadTemplatesFromDirectory(file);
            return;
        }




            try {

                TemplateDescriptor td = new TemplateDescriptor();
                td.setDisplayName(file.getName());
                td.setFile(file);

              //  TemplateCategory cat = addCategory(jd.getProperty("categoty") != null ?  jd.getProperty("categoty") : "Others",
              //             jd.getProperty("subCategory") != null ?  jd.getProperty("subCategory") : "Custom");

               // td.setCategory(cat);

                td.setPageSize( new Dimension(200, 300)); //????


                addTemplate(td);
            } catch (Exception ex) {

                Exceptions.printStackTrace(ex);
            }

    }

    public void loadTemplatesFromDirectory(File folder)
    {
        if (folder != null && folder.exists())
        {
            File[] files = folder.listFiles(new FileFilter() {

                public boolean accept(File pathname) {
                	String filename = pathname.getName().toLowerCase();
                	return (filename.endsWith("c.xml") || filename.endsWith("t.xml"));
                }
            });

            for (int i=0; i<files.length; ++i)
            {
                loadTemplatesFromFile(files[i]);
            }
        }
    }

    /**
     * @return the fullPageView
     */
    public boolean isFullPageView() {
        return fullPageView;
    }

    /**
     * @param fullPageView the fullPageView to set
     */
    public void setFullPageView(boolean fullPageView) {

        this.fullPageView = fullPageView;
        if (fullPageView)
        {
            jList1ComponentResized(null);
            jList1.ensureIndexIsVisible(0);
            jList1.ensureIndexIsVisible(jList1.getSelectedIndex());
        }

    }

    public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setLayout(new BorderLayout());
		jf.setPreferredSize(new Dimension(500,600));
		jf.add(new TemplatesPanel());
		//jf.sete
		jf.setVisible(true);

	}

}
