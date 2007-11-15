PYGTK=~/vcs/gnome2/pygtk
MISSING=missing.txt
DEFSDIR=../../defs

all: demux blacklist install

demux:
	rm -f *.defs
	rm -f $(MISSING)

	./demux.py java-gnome.fixes >> $(MISSING)

	./demux.py $(PYGTK)/gtk/gdk-base.defs >> $(MISSING)
	./demux.py $(PYGTK)/gtk/gdk-2.10.defs >> $(MISSING)

	./demux.py $(PYGTK)/gtk/gtk-base.defs >> $(MISSING)
	./demux.py $(PYGTK)/gtk/gtk-2.10.defs >> $(MISSING)

	./demux.py $(PYGTK)/gtk/libglade.defs >> $(MISSING)
	./demux.py $(PYGTK)/gtk/gtkunixprint.defs >> $(MISSING)
	./demux.py $(PYGTK)/pango.defs >> $(MISSING)
	./demux.py $(PYGTK)/atk.defs >> $(MISSING)

blacklist:
	rm -f PyGtkGenericCellRenderer.defs
	rm -f PyGtkGenericTreeModel.defs
	rm -f GtkProgress.defs
	rm -f GtkList.defs
	rm -f GtkCTree.defs
	rm -f GtkCTreeExpanderStyle.defs
	rm -f GtkCTreeExpansionType.defs
	rm -f GtkCTreeLineStyle.defs
	rm -f GtkCTreePos.defs
	rm -f GtkType.defs
	rm -f GtkText.defs
	rm -f GtkCombo.defs
	rm -f GtkOldEditable.defs
	rm -f GtkArgs.defs
	rm -f GtkPixmap.defs

install:
	mv *.defs $(DEFSDIR)
	mv $(MISSING) ../missing.defs

